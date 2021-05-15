package com.tennistourcol.ui.tournament;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tennistourcol.R;
import com.tennistourcol.model.Response;
import com.tennistourcol.model.Tournament;
import com.tennistourcol.service.TournamentService;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditTorunamentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditTorunamentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TournamentService tournamentService;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditTorunamentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditTorunamentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditTorunamentFragment newInstance(String param1, String param2) {
        EditTorunamentFragment fragment = new EditTorunamentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_torunament, container, false);
        // Inflate the layout for this fragment
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ieti-back.herokuapp.com/") //localhost for emulator
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tournamentService = retrofit.create(TournamentService.class);
        ((FloatingActionButton) view.findViewById(R.id.deleteTournamentButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                deleteTournament(Tournament.builder().id("1").build());
                System.out.println("ELIMINAR-----------------");
            }
        });

        /*
        confirmarboton = (Button) view.findViewById(R.id.button);
        confirmarboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Confirmacion de Edicion Torneo";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        container.getContext()
                )
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle("Nueva Notifiacion")
                        .setContentText(message)
                        .setAutoCancel(true);
                Intent intent = new Intent(container.getContext(), NotifiactionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message",message);

                PendingIntent pendingIntent = PendingIntent.getActivities(container.getContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,builder.build());
            }

        });
         */

        return view;
    }

    private void deleteTournament(Tournament tournament) {
        executorService.execute(new Runnable()
        {
            @Override
            public void run() {
                retrofit2.Response<Response> response = null;
                try {
                    response = tournamentService.deleteTournament(tournament).execute();
                    System.out.println("res " + response.toString());
                    com.tennistourcol.model.Response tournamentResponse = response.body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }});
    }
}